# 我是readme文件，读我  
### 使用方式
```
1. 依赖im_lib库，implementation project(':im_lib')
2. 自定义IMSEventListener，实现OnEventListener，重写对应的方法配置参数
3. 自定义IMSConnectStatusListener，实现IMSConnectStatusCallback，重现对应的方法监听ims连接状态
4. 调用IMSClientInterface.init(Vector<String> serverUrlList, OnEventListener listener, IMSConnectStatusCallback callback)方法，把服务器地址列表、IMSEventListener、IMSConnectStatusListener三个参数传入即可。
5. 发送消息：调用IMSClientInterface.sendMsg(MessageProtobuf.Msg msg)即可发送
6. 接收消息：收到消息会回调IMSEventListener.dispatchMsg(MessageProtobuf.Msg msg)方法。
```

注：由于jcenter账号一直申请不了，所以目前可以先通过下载源码方式进行依赖，后续会发布到jcenter上，以gradle方式进行依赖。

## 项目博客地址：
[掘金](https://juejin.im/post/5c97ae12e51d45580b681b0b)
[简书](https://www.jianshu.com/p/00ba0ac2fc96)
[CSDN](https://blog.csdn.net/FreddyChen/article/details/89201785)

## 使用过程中，如果有任何疑问，请联系我。
## 如果该项目对你有用，麻烦star一下哈。。。
## QQ交流群：1015178804，目前是Android IM技术交流群，后续写的文章，也会用此群进行交流。
## 目前准备写的文章如下：
```
~~1.《开源一个自用的Android IM库，基于Netty+TCP+Protobuf实现》~~
2.《开源一个自用的Android IM库，基于Netty+WebSocket+Protobuf实现》
3.《开源一个自用的Android IM库，基于Netty+UDP+Protobuf实现》
4.《开源一个自用的Android网络请求库，基于Rxjava+Retrofit实现》
5.《开源一个自用的Android线程池，基于ThreadPoolExecutor实现》
6.《开源一个自用的Android IM UI界面，包含文本、图片、语音、表情、红包等实现》
7.《开源一个自用的Android图片加载库，基于Glide实现》
8.《开源一个自用的Android视频压缩库，基于MediaCodec实现》
9.《开源一个自用的Android视频压缩库，基于ffmpeg实现》
10.《开源一个自用的Android事件分发中心库，基于对象池实现》
```
以上文章没有先后顺序，想到哪就写到哪吧。

# 再次感谢鸿洋大神和郭霖大神。虽然文章太长，无法投稿公众号，但这两天star量还是增加了不少，非常感谢鸿洋大神在wanandroid上的推荐。

# License


    Copyright 2019, chenshichao       
  
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at 
 
       http://www.apache.org/licenses/LICENSE-2.0 

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
