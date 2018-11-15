package wallet;

import utils.PropertiesUtils;

/**
 * @author Niu Li
 * @date 2016/11/10
 */
public final class WalletConfig {

    public static  String APP_PRIVATEKEY = PropertiesUtils.getUserDirProperty("app_privatekey", "wallet.properties");
}
