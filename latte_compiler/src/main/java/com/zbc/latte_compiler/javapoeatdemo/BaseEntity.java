package com.zbc.latte_compiler.javapoeatdemo;

import java.lang.String;

/**
 * 请求实体Bean */
class BaseEntity {
  private int code;

  private String msg;

  private DataBean data;

  public int getCode() {
    return this.code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public DataBean getData() {
    return this.data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  /**
   * 内部类生成 */
  static class DataBean {
    public String name;

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
