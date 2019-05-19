# day01
---
- 欢迎页
- 引导页
- 项目主架构

# 欢迎页
需求

1. 展示2秒钟跳转到主页面
2. 透明度从0~1 

技术选型

1. 透明动画AlphaAnimation
2. 动画监听AnimationListener
3. 界面跳转startActivity(Intent(this, MainActivit.class))

代码实现

~~~
activity_splash.xml
~~~
~~~
SplashActivity.java
~~~

总结

~~~
欢迎页的实现方式有很多种，需要根据需求选择不同的技术去实现。一般情况下都是延时跳转。
~~~




