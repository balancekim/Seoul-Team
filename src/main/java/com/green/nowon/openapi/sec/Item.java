package com.green.nowon.openapi.sec;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class Item {
	@JsonProperty("CLTUR_EVENT_ETC_NM")
	private String clturEventEtcNm;

	@JsonProperty("SVC_CL_CODE")
	private String svcClCode;

	@JsonProperty("SVC_CL_NM")
	private String svcClNm;

	@JsonProperty("ATDRC_CODE")
	private String atdrcCode;

	@JsonProperty("ATDRC_NM")
	private String atdrcNm;

	@JsonProperty("AGE_SE_CODE")
	private String ageSeCode;

	@JsonProperty("AGE_SE_NM")
	private String ageSeNm;

	@JsonProperty("FCLTY_CL_CODE")
	private String fcltyClCode;

	@JsonProperty("FCLTY_CL_NM")
	private String fcltyClNm;

	@JsonProperty("X_CRDNT_VALUE")
	private String xCrdntValue;

	@JsonProperty("Y_CRDNT_VALUE")
	private String yCrdntValue;

	@JsonProperty("ZIP")
	private String zip;

	@JsonProperty("BASS_ADRES")
	private String bassAdres;

	@JsonProperty("DETAIL_ADRES")
	private String detailAdres;

	@JsonProperty("RNTFEE_FREE_AT")
	private String rntfeeFreeAt;

	@JsonProperty("RNTFEE")
	private String rntfee;

	@JsonProperty("GUIDANCE_URL")
	private String guidanceUrl;

	@JsonProperty("REGIST_DT")
	private String registDt;

	@JsonProperty("UPDT_DT")
	private String updtDt;
	
	
}
