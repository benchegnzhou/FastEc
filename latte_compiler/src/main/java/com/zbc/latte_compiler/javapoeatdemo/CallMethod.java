package com.zbc.latte_compiler.javapoeatdemo;

/**
 * 网络请求数据的调用 */
class CallMethod {
  /**
   * loadData 方法 */
  public void loadData() {
     String url = "http://www.baidu.com";
    new DataClient()
        .build()
       .setTag(this.getClass().getCanonicalName())
       .get(url)
       .execute(new BaseCallback(){
           @Override
           public void onStart() {

           }
           @Override
           public void onFinish() {

           }

           @Override
           public void onError(int errorCode, String msg) {

           }

           @Override
           public void onSuccess(Object o) {

           }
     }); 
  }
}
