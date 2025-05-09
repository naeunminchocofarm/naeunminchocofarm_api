package com.naeunminchocofarm.ncf_api.member.controller;

import com.naeunminchocofarm.ncf_api.lib.exception.ApiException;
import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.lib.security.AuthInfo;
import com.naeunminchocofarm.ncf_api.lib.security.AuthUser;
import com.naeunminchocofarm.ncf_api.member.dto.*;
import com.naeunminchocofarm.ncf_api.member.entity.*;
import com.naeunminchocofarm.ncf_api.member.service.MemberService;
import com.naeunminchocofarm.ncf_api.smart_farm.dto.SimpleFarmDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.service.FarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@RestController
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	private final FarmService farmService;

	public MemberController(MemberService memberService, PasswordEncoder passwordEncoder, FarmService farmService) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
		this.farmService = farmService;
	}

	@GetMapping("/admin/members")
	public List<MemberDTO> getMemberList(@RequestParam(value = "page", defaultValue = "1") Integer page,
																			 @RequestParam(value = "size", defaultValue = "10") Integer size) {
		try {
			Pagination pagination = new Pagination(size, page);
			return memberService.getMemberList(pagination);
		} catch (IllegalArgumentException ex) {
			throw new ApiException(ex.getMessage(), "BAD_REQUEST", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/member/farms")
	public List<SimpleFarmDTO> getMemberFarms(@AuthInfo AuthUser authUser) {
		return farmService.getFarmsByMemberId(authUser.getId());
	}

	@GetMapping("/member/farms/{id}")
	public Optional<SimpleFarmDTO> getMemberFarms(@AuthInfo AuthUser authUser, @PathVariable("id") Integer farmId) {
		return farmService.getFarmByIdAndMemberId(farmId, authUser.getId());
	}

	@GetMapping("/member/memberInfo")
	public Optional<MemberInfoDTO> getMemberInfo(@AuthInfo AuthUser authUser) {
		return memberService.getMemberInfo(authUser.getId());
	}

	@GetMapping("/member/profile-img")
	public ResponseEntity<MemberImgDTO> getMemberProfileImg(@AuthInfo AuthUser authUser) {
		MemberImg memberImg = memberService.getMemberImgByMemberId(authUser.getId());
		if (memberImg == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(new MemberImgDTO(
						memberImg.getImgId(),
						memberImg.getMemberId(),
						memberImg.getOriginFileName(),
						memberImg.getAttachedFileName()
		));
	}

	@PostMapping("/member/profile-img")
	public ResponseEntity<Void> insertMemberProfileImg(@AuthInfo AuthUser authUser, @RequestBody MemberImgDTO memberImgDTO) {
		MemberImg memberImg = new MemberImg(null, authUser.getId(),
						memberImgDTO.getOriginFileName(),
						memberImgDTO.getAttachedFileName());
		memberService.insertMemberImg(memberImg);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/member/profile-img")
	public ResponseEntity<Void> updateMemberProfileImg(@AuthInfo AuthUser authUser, @RequestBody MemberImgDTO memberImgDTO) {
		MemberImg memberImg = new MemberImg(memberImgDTO.getImgId(), authUser.getId(),
						memberImgDTO.getOriginFileName(),
						memberImgDTO.getAttachedFileName());
		memberService.updateMemberImg(memberImg);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/member/profile-img")
	public ResponseEntity<Void> deleteMemberProfileImg(@AuthInfo AuthUser authUser) {
		memberService.deleteMemberImgByMemberId(authUser.getId());
		return ResponseEntity.ok().build();
	}

	// 프로필 이미지 업로드 (MultipartFile 처리)
	@PostMapping("/member/profile-img/upload")
	public ResponseEntity<MemberImgDTO> uploadProfileImg(
					@AuthInfo AuthUser authUser,
					@RequestParam("file") MultipartFile file) {

		try {
			System.out.println("[업로드 시작] 파일 수신: " + file);

			if (file == null || file.isEmpty()) {
				System.out.println("[업로드 실패] 파일이 비어 있음");
				throw new ApiException("업로드된 파일이 비어 있습니다.", "EMPTY_FILE", HttpStatus.BAD_REQUEST);
			}

			// 1. 파일명
			String originFileName = file.getOriginalFilename();
			System.out.println("[원본 파일명] " + originFileName);

			if (originFileName == null || originFileName.isBlank()) {
				throw new ApiException("파일 이름이 비어 있습니다.", "INVALID_FILE_NAME", HttpStatus.BAD_REQUEST);
			}

			// 2. 확장자
			String ext = StringUtils.getFilenameExtension(originFileName);
			if (ext == null || ext.isBlank()) {
				throw new ApiException("확장자가 없습니다.", "NO_EXTENSION", HttpStatus.BAD_REQUEST);
			}

			// 3. 저장 파일명
			String attachedFileName = UUID.randomUUID() + "." + ext;

			// 4. 저장 경로
			String uploadDir = "D:/uploads"; // 실제 존재하는 디렉토리
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
				System.out.println("[디렉토리 생성] " + uploadDir);
			}

			// 5. 저장
			Path savePath = uploadPath.resolve(attachedFileName);
			Files.copy(file.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("[파일 저장 완료] " + savePath.toString());

			// 6. 응답 DTO 생성
			return ResponseEntity.ok(new MemberImgDTO(
							null,
							authUser.getId(),
							originFileName,
							attachedFileName
			));

		} catch (IOException e) {
			e.printStackTrace();
			throw new ApiException("파일 저장 실패", "IO_EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("파일 업로드 실패", "UPLOAD_FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
