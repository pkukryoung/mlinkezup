package com.kdj.mlink.ezup3.shop.domesin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponseDto {
	@JsonProperty("code")
	public String code;
	@JsonProperty("m_id")
	public String m_id;
	@JsonProperty("api_key")
	public String api_key;
	@JsonProperty("notice_url")
	public String notice_url;

	@Override
	public String toString() {
		return "LoginResponse [code=" + code + ", m_id=" + m_id + ", api_key=" + api_key + ", notice_url="
				+ notice_url + "]";
	}
}
