package com.example.webclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupportDTO {

     @JsonProperty("url")
     private String url;

     @JsonProperty("text")
     private String text;

     @JsonProperty("url")
     public String getUrl() {
          return url;
     }

     @JsonProperty("url")
     public void setUrl(String url) {
          this.url = url;
     }

     @JsonProperty("text")
     public String getText() {
          return text;
     }

     @JsonProperty("text")
     public void setText(String text) {
          this.text = text;
     }

     @Override
     public String toString() {
          return "SupportDTO{" +
                  "url='" + url + '\'' +
                  ", text='" + text + '\'' +
                  '}';
     }
}
