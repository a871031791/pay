package entity;

/**
 * 
 *
 * 场景信息 //WAP网站应用 {"h5_info": {"type":"Wap","wap_url":
 * "https://pay.qq.com","wap_name": "腾讯充值"}}
 * 
 * @author ThinkPad
 *
 */
public class SceneInfo {

	private H5Info h5_info;

	public void setH5_info(H5Info h5_info) {
		this.h5_info = h5_info;
	}

	public H5Info getH5_info() {
		return h5_info;
	}

	public static class H5Info {
		private String type;
		private String wap_url;
		private String wap_name;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getWap_url() {
			return wap_url;
		}

		public void setWap_url(String wap_url) {
			this.wap_url = wap_url;
		}

		public String getWap_name() {
			return wap_name;
		}

		public void setWap_name(String wap_name) {
			this.wap_name = wap_name;
		}

	}

}
