<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>websocket</title>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px;
        }
    </style>
    <script type="text/javascript">
        var websocket = null;
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            //建立连接，这里的/websocket ，是Servlet中注解中的那个值，一定对应哈
            websocket = new WebSocket("ws://localhost:8066/ZJJGWeb_war/websocket");//这里的项目名称，有的人进行了设置，有的人没有进行设置，还是根据自己的项目情况来

        }
        else {
            alert('当前浏览器不支持websocket');
        }
        //连接发生错误的回调方法
        websocket.onerror = function () {
            document.write("WebSocket connect error<br>")
        };
        //连接成功建立的回调方法
        websocket.onopen = function () {
            document.write("WebSocket connect success<br>");
        }
        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            //我在编写逻辑时，如果后端数据库发生变化，就给前端传送的数据设置为1，
            if(event.data=="1"){
                //这里就可以在前端进行相应的功能展示，我这里就只用打印一句话进行表示了
                document.write("data is updated<br>");

            }
        }
        //连接关闭的回调方法
        websocket.onclose = function () {
            document.write("WebSocket connect closed<br>");
        }
        //监听窗口关闭事件，当窗口关闭时，主动去关闭WebSocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            closeWebSocket();
        }
        //关闭WebSocket连接
        function closeWebSocket() {
            websocket.close();
        }
    </script>
</head>
<body>
Websokcet test start：
</body>
</html>
