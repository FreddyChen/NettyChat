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
