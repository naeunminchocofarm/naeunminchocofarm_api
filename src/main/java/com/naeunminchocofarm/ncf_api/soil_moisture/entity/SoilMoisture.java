package com.naeunminchocofarm.ncf_api.soil_moisture.entity;

import java.time.OffsetDateTime;

	public class SoilMoisture {
		private Integer id;
		private Integer soilMoistureValue;
		private OffsetDateTime measuredAt;

		public SoilMoisture() {

		}

		public SoilMoisture(Integer id, Integer soilMoistureValue, OffsetDateTime measuredAt) {
			this.id = id;
			this.soilMoistureValue = soilMoistureValue;
			this.measuredAt = measuredAt;
		}

		public Integer getId() {
			return id;
		}

		public Integer getSoilMoistureValue() {
			return soilMoistureValue;
		}

		public OffsetDateTime getMeasuredAt() {
			return measuredAt;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public void setSoilMoistureValue(Integer soilMoistureValue) {
			this.soilMoistureValue = soilMoistureValue;
		}

		public void setMeasuredAt(OffsetDateTime measuredAt) {
			this.measuredAt = measuredAt;
		}
	}
