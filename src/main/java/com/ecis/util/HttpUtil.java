//package com.ecis.util;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.InetAddress;
//import java.net.URLEncoder;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.utils.URLEncodedUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.github.kevinsawicki.http.HttpRequest;
//import egovframework.rte.fdl.property.EgovPropertyService;
//
///**
// * https://github.com/kevinsawicki/http-request
// * 
// * @author 기술연구소 선임 김태용
// *
// */
//public class HttpUtil {
//    private static final Logger              logger  = LoggerFactory.getLogger(HttpUtil.class);
//    private static final String              CHARSET = "UTF-8";
//    private static final int                 TIMEOUT = 5000;
//    private static InetAddress               inet    = null;
//
//    public static String get(String url) {
//        return HttpUtil.get(url, TIMEOUT);
//    }
//
//    public static String get(String url, Map<String, Object> params) {
//        List<NameValuePair> list = ObjectUtil.convertParam(params);
//        url = url + "?" + URLEncodedUtils.format(list, "UTF-8");
//        return HttpUtil.get(url, TIMEOUT);
//    }
//
//    public static String get(String url, String encoding) {
//        return HttpUtil.get(url, TIMEOUT);
//    }
//
//    public static String get(String url, int timeout) {
//        HttpRequest request = null;
//        try {
//            if (StringUtil.isEmpty(url)) {
//                throw new Exception("URL 값이 비어있습니다. 다시 확인해 주십시오.");
//            }
//            String body = "";
//            request = HttpRequest.get(url);
//            request.connectTimeout(timeout);
//            if (request.ok()) {
//                body = request.body();
//            } else {
//                throw new RuntimeException();
//            }
//            return body;
//        } catch (Exception e) {
//            logger.error("Http connection error, request.code() : {}, url : {}, timeout: {}, 요청시 에러 발생, Exception: {}", (request != null ? request.code() : null), url, timeout, e.getMessage());
//            return e.getMessage();
//        }
//    }
//
//    public static String post(String url) {
//        return HttpUtil.post(url, null, CHARSET, TIMEOUT);
//    }
//
//    public static String post(String url, Map<String, String> params) {
//        return HttpUtil.post(url, params, CHARSET, TIMEOUT);
//    }
//
//    public static String post(String url, Map<String, String> params, String charset) {
//        return HttpUtil.post(url, params, charset, TIMEOUT);
//    }
//
//    public static String post(String url, Map<String, String> params, int timeout) {
//        return HttpUtil.post(url, params, CHARSET, timeout);
//    }
//
//    public static String post(String url, Map<String, String> params, String charset, int timeout) {
//        try {
//            String body = "";
//            HttpRequest request = HttpRequest.post(url);
//            request.connectTimeout(timeout);
//            if (params != null && !params.isEmpty()) {
//                request.form(params, charset);
//            }
//            if (request.ok()) {
//                body = request.body();
//            } else {
//                logger.error("Http connection error : {}", request.code());
//            }
//            return body;
//        } catch (Exception e) {
//            logger.error("url : {} 요청시 에러 발생,params:{}, e:{}", url, params, e.getMessage());
//            return e.getMessage();
//        }
//    }
//
//    public static String getServerIp() {
//        try {
//            if (inet == null) {
//                inet = InetAddress.getLocalHost();
//            }
//            return inet.getHostAddress();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//            return "255.255.255.255";
//        }
//    }
//
//    public static String getClientIp() {
//        return getClientIp(SessionUtil.getRequest());
//    }
//
//    public static String getClientIp(HttpServletRequest request) {
//        if (request == null) {
//            request = SessionUtil.getRequest();
//            if (request == null) {
//                return "Unknown IP(Request 객체가 없음)";
//            }
//        }
//        String clientIp = request.getHeader("X-Forwarded-For");
//        if (isEmptyClientIp(clientIp)) {
//            clientIp = request.getHeader("Proxy-Client-IP");
//        }
//        if (isEmptyClientIp(clientIp)) {
//            clientIp = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (isEmptyClientIp(clientIp)) {
//            clientIp = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (isEmptyClientIp(clientIp)) {
//            clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (isEmptyClientIp(clientIp)) {
//            clientIp = request.getRemoteAddr();
//        }
//        return clientIp;
//    }
//
//    public static boolean isEmptyClientIp(String clientIp) {
//        return StringUtil.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp);
//    }
//
//    /**
//     * 인자를 분석하여 Content Type을 반환함.
//     * 
//     * @param targetInfo
//     * @return
//     */
//    public static String getContentType(String targetInfo) {
//        try {
//            return fileExtensionMap.get(targetInfo.substring(targetInfo.lastIndexOf(".") + 1));
//        } catch (Exception e) {
//            return "text/plain";
//        }
//    }
//
//    // public static boolean isBandSite() throws Exception {
//    // init();
//    // String domain = getNirisDomain();
//    // return (domain != null && domain.indexOf("band.dongkuk.com") != -1) ? true : false;
//    // }
//    //
//    // public static String getMailDomain() throws Exception {
//    // init();
//    // return propertyService.getString("service.url.mail");
//    // }
//    //
//    // /**
//    // * @Method 설명 : url 모바일 요청 인지 체크
//    // * @param request
//    // * @return
//    // */
//    // public static boolean isNirisMobileApp(HttpServletRequest request) {
//    // try {
//    // String url = request.getRequestURL().toString();
//    // if (url.indexOf("m.niris") > 0) {
//    // return true;
//    // }
//    // } catch (Exception e) {
//    // return false;
//    // }
//    // return false;
//    // }
//    //
//    // /**
//    // * 서버에 따라 도메인주소를 return 함.
//    // *
//    // * niris.dongkuk.com 또는 band.dongkuk.com
//    // *
//    // * @return
//    // * @throws Exception
//    // */
//    // public static String getNirisDomain() throws Exception {
//    // init();
//    // return propertyService.getString("service.url.core");
//    // }
//
//    public static boolean isMobile(HttpServletRequest request) {
//        String ua = request.getHeader("User-Agent").toLowerCase();
//        return ua
//                .matches("(?i).*((android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows ce|xda|xiino).*")
//                || ua.substring(0, 4)
//                        .matches("(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-");
//    }
//
//    private static void init() throws Exception {
//        if (propertyService == null) {
//            propertyService = (EgovPropertyService) ProxyFactory.createService(EgovPropertyService.class);
//        }
//    }
//
//    /**
//     * ServletRequest#getLocalName() returns local hostname.
//     * ServletRequest#getLocalAddr() returns local IP. ServletRequest#getLocalPort()
//     * returns local port.
//     * 
//     * @return
//     */
//    public static int getServerPort() {
//        HttpServletRequest request = SessionUtil.getRequest();
//        return request.getLocalPort();
//    }
//
//    /**
//     * json 형태의 header 정보와 parameter 정보를 return한다.
//     * 
//     * @param request
//     * @return
//     */
//    public static String getHeadersNParams(HttpServletRequest request) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("headers", getHeader(request));
//        map.put("params", getParameter(request));
//        String returnStr = null;
//        try {
//            returnStr = ObjectUtil.writeValueAsStringPretty(map);
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return returnStr;
//    }
//
//    /**
//     * parameter 정보를 map 형태로 return 한다.
//     * 
//     * @param request
//     * @return
//     */
//    public static Map<String, Object> getParameter(HttpServletRequest request) {
//        boolean isMultipartForm = isMultipartForm(request);
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        try {
//            Enumeration<String> paramNames = (Enumeration<String>) request.getParameterNames();
//            String paramName = null;
//            while (paramNames.hasMoreElements()) {
//                paramName = paramNames.nextElement();
//                String[] values = request.getParameterValues(paramName);
//                if (values != null && values.length == 1) {
//                    if (isMultipartForm) {
//                        paramMap.put(paramName, StringUtil.toUTF8(values[0]));
//                    } else {
//                        paramMap.put(paramName, values[0]);
//                    }
//                } else {
//                    if (isMultipartForm) {
//                        String[] UTF8_values = new String[values.length];
//                        for (int i = 0; i < values.length; i++) {
//                            UTF8_values[i] = StringUtil.toUTF8(values[i]);
//                        }
//                        paramMap.put(paramName, UTF8_values);
//                    } else {
//                        paramMap.put(paramName, values);
//                    }
//                }
//            }
//        } catch (Exception e) {
//        }
//        return paramMap;
//    }
//
//    public static boolean isMultipartForm(HttpServletRequest request) {
//        return StringUtil.nvl(request.getContentType()).indexOf("multipart/form-data") >= 0;
//    }
//
//    /**
//     * header 정보를 map 형태로 return 한다.
//     * 
//     * @param request
//     * @return
//     */
//    public static Map<String, Object> getHeader(HttpServletRequest request) {
//        Map<String, Object> headerMap = new HashMap<String, Object>();
//        try {
//            Enumeration<String> headerNames = (Enumeration<String>) request.getHeaderNames();
//            String headerName = null;
//            while (headerNames.hasMoreElements()) {
//                headerName = headerNames.nextElement();
//                headerMap.put(headerName, request.getHeader(headerName));
//            }
//        } catch (Exception e) {
//        }
//        return headerMap;
//    }
//
//    public static String getQueryString(HttpServletRequest request) {
//        QueryString qs = new QueryString();
//        boolean isMultipartForm = isMultipartForm(request);
//        try {
//            Enumeration<String> paramNames = (Enumeration<String>) request.getParameterNames();
//            String paramName = null;
//            while (paramNames.hasMoreElements()) {
//                paramName = paramNames.nextElement();
//                String[] values = request.getParameterValues(paramName);
//                if (values.length > 1) {
//                    if (isMultipartForm) {
//                        for (String value : values) {
//                            qs.add(paramName, StringUtil.toUTF8(value));
//                        }
//                    } else {
//                        for (String value : values) {
//                            qs.add(paramName, value);
//                        }
//                    }
//                } else if (values.length == 1) {
//                    if (isMultipartForm) {
//                        qs.add(paramName, StringUtil.toUTF8(values[0]));
//                    } else {
//                        qs.add(paramName, values[0]);
//                    }
//                } else {
//
//                }
//            }
//        } catch (Exception e) {
//        }
//        return qs.getFullURL(request.getRequestURL().toString());
//    }
//
//    public static String getQueryString(HttpServletRequest request, String domainUrl) {
//        QueryString qs = new QueryString();
//        boolean isMultipartForm = isMultipartForm(request);
//        try {
//            Enumeration<String> paramNames = (Enumeration<String>) request.getParameterNames();
//            String paramName = null;
//            while (paramNames.hasMoreElements()) {
//                paramName = paramNames.nextElement();
//                String[] values = request.getParameterValues(paramName);
//                if (values.length > 1) {
//                    if (isMultipartForm) {
//                        for (String value : values) {
//                            qs.add(paramName, StringUtil.toUTF8(value));
//                        }
//                    } else {
//                        for (String value : values) {
//                            qs.add(paramName, value);
//                        }
//                    }
//                } else if (values.length == 1) {
//                    if (isMultipartForm) {
//                        qs.add(paramName, StringUtil.toUTF8(values[0]));
//                    } else {
//                        qs.add(paramName, values[0]);
//                    }
//                } else {
//
//                }
//            }
//        } catch (Exception e) {
//        }
//        return qs.getFullURL(request.getRequestURL().toString().replaceAll("http://webusan.ecis.co.kr", domainUrl));
//    }
//
//    public static void main(String[] args) throws Exception {
//        // Map<String, Object> resultMap1 =
//        // ObjectUtil.getJson("http://nmail.dongkuk.com/open/api/mail/count/json?emailId=taeyong1.kim@dongkuk.com");
//        // Map<String, Object> resultMap2 =
//        // ObjectUtil.getJson("http://dmail.dongkuk.com/open/api/mail/count/json?emailId=taeyong1.kim@dongkuk.com");
//        // String result =
//        // HttpUtil.get("http://nmail.dongkuk.com/open/api/mail/count/json?emailId=dongchul.kim@dongkuk.com");
//        // Map<String,Object> resultMap =
//        // ObjectUtil.readValue(Jsoup.parse(result).text(), Map.class);
//        // logger.error("{}",result);
//        // logger.error("{}",Jsoup.parse(result).text());
//        // logger.error("{}",(Integer) resultMap.get("newMailCount"));
//
//        QueryString qs = new QueryString();
//        qs.add("kl", "XX");
//        qs.add("stype", "stext");
//        qs.add("q", "+\"Java Programming\"");
//        logger.error(qs.getFullURL("http://www.java.com/query"));
//
//        /*
//         * int count = 0; while (true) { count++; String html =
//         * HttpUtil.get("http://www.baidu.com:85/moveLogin.do"); logger.error(html); if
//         * (count == 10) { return; } }
//         */
//        // String html = HttpUtil.get("http://blog.dongkuk.com/");
//        // String result =
//        // HttpUtil.post("http://172.31.5.38:8080/appr/approveListTotalAjax.json?i_pernr=11070112&i_aptyp=1");
//        // logger.error(result);
//        /*
//         * URL url = new URL(
//         * "http://172.31.5.38:8080/appr/approveListTotalAjax.json?i_pernr=11070112&i_aptyp=1"
//         * ); URLConnection yc = url.openConnection(); BufferedReader in = new
//         * BufferedReader(new InputStreamReader(yc.getInputStream())); String inputLine;
//         * String tempValue = ""; while ((inputLine = in.readLine()) != null) tempValue
//         * += inputLine; in.close(); logger.error(tempValue);
//         */
//    }
//}
//
//class QueryString {
//    private List<String> queryList = new ArrayList<String>();
//
//    public void add(String name, String value) {
//        queryList.add(encode(name, value));
//    }
//
//    private String encode(String name, String value) {
//        try {
//            return URLEncoder.encode(name, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
//        } catch (UnsupportedEncodingException ex) {
//            throw new RuntimeException("Broken VM does not support UTF-8");
//        }
//    }
//
//    public String getQueryString() {
//        return StringUtils.join(queryList, "&");
//    }
//
//    public String getFullURL(String domainUrl) {
//        if (queryList.size() == 0) {
//            return domainUrl;
//        } else {
//            return domainUrl + "?" + StringUtils.join(queryList, "&");
//        }
//    }
//
//    public String toString() {
//        return getQueryString();
//    }
//}
