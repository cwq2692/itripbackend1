package com.cwq.wx;

import org.springframework.stereotype.Component;

/**
 * @Author:崔文青
 * @Description:
 * @Date:Created in 19:08 2019/5/22
 */
@Component
public class WxUtils {
    public String appid = "wx9168f76f000a0d4c";
    public String redirect_uri = "http%3a%2f%2flocalhost%3a8888%2fwxloginCallback";
    public String response_type = "code";
    public String scope = "snsapi_login";
    public String state = "STATE#wechat_redirect";
    public String secret = "8ba69d5639242c3bd3a69dffe84336c1";

    public WxUtils() {
    }
}
