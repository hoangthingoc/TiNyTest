package admin;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "useGrid", "gridAddress", "appBaseUrl", "appUsername", "appPassword"})
public class Configs {
	public String useGrid;
	public String gridAddress;
	public String appBaseUrl;
	public String appUsername;
	public String appPassword;
	public String appUserNameStaff;
	public String appPasswordStaff;

}