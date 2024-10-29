package com.example.NewsAggregator.NewsAggreagatorUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class UrlBuilder {

    /**
     *
     * @param baseUrl   baseUrl which we will be passing to prepare the more advance URL.
     * @param queryParams extra inputs which we need to add to the baseURL
     * @return return the URL which will help us to get the data.
     */
    public static String buildUrl(String baseUrl, Map<String, String> queryParams)
    {
        StringBuilder url = new StringBuilder(baseUrl);
        if(!queryParams.isEmpty())
        {
            url.append("?");
            queryParams.forEach((key, value)->{
                url.append(URLEncoder.encode(key, StandardCharsets.UTF_8))
                        .append("=")
                        .append(URLEncoder.encode(value, StandardCharsets.UTF_8))
                        .append("&");
            });
            url.setLength(url.length()-1);
        }
        return url.toString();
    }
}
