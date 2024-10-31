package com.example.repair_severs.filter;


import com.example.repair_severs.entity.msgCode;
import com.example.repair_severs.util.redis_static;
import com.example.repair_severs.entity.Repairrequest;
import com.example.repair_severs.util.redis_static;
import com.google.gson.Gson;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

//处理夸域请求和验证合法性
@Configuration
public class CorsConfig
  implements Filter
{
  //这是一个实现 Filter 接口的 doFilter 方法。此方法用于在处理 HTTP 请求和响应时执行某些操作。
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
    throws IOException, ServletException
  {

    HttpServletRequest request = (HttpServletRequest)req;
    HttpServletResponse response = (HttpServletResponse)resp;
    response.setContentType("text/html; charset=utf-8");
    request.setCharacterEncoding("utf-8");

    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", -10);
    response.setHeader("Pragma", "No-cache");
    // 设置了一些http头用于响应跨服区域响应
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET,PUT, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Headers", "*");

    String url = ((HttpServletRequest)req).getRequestURI();//路径

    Logger.getGlobal().info(url);//打印

    msgCode msgcode = new msgCode();//msgcode 返回错误信息
    if (url != null)

    {
      if (
              //如果是这一段表单里面的那就直接放行
              ("/user/adduser".equals(url)||"/user/login".equals(url)||"/repairrequest/post".equals(url)||
      "/user/mylogin".equals(url)||"/user/uploadexcel".equals(url)||"/Loadexcel.html".equals(url)|| "/user/upzhloadexcel".equals(url)||"/upload.html".equals(url)
              ))

      {
        Logger.getGlobal().info("不拦截：直接放行");
        chain.doFilter(req, resp);
        return;
      }



      Logger.getGlobal().info("拦截：要验证");
      String imei = request.getHeader("imei");
      String tokens = request.getHeader("tokens");

      String tokensrc = redis_static.getIntence().getsrc(tokens);//获取token

      if ((imei == null) || (imei.equals("")) || (tokens == null) || (tokens.equals("")))
      {
        response.setStatus(401);
        PrintWriter out = resp.getWriter();//创建一个向客户端发送文本客户端发送字符文本响应
        msgcode.setMsg("tokens或imei为空");
        msgcode.setCode(300);
        msgcode.setObj("tokensrc：" + tokensrc + "  tokens:" + tokens + "  imei:" + imei);
        out.println(new Gson().toJson(msgcode));//输出为json格式
        out.close();

      }


      else if (tokensrc != null)
      {

        tokensrc = tokensrc.trim();//去除字符串 tokensrc 的前后空白字符
        if (tokensrc.equals(imei))
        {
          Logger.getGlobal().info("6.效验已登录：");
          chain.doFilter(request, response);//传递过滤器
        }
        else
        {

          response.setStatus(401);
          PrintWriter out = resp.getWriter();
          msgcode.setMsg("tokens过期");
          msgcode.setCode(300);
          msgcode.setObj("");
          out.println(new Gson().toJson(msgcode));
          out.close();

        }
      }
      else
      {
        response.setStatus(401);
        PrintWriter out = resp.getWriter();
        msgcode.setMsg("tokens不存在");
        msgcode.setCode(300);
        msgcode.setObj("");
        out.println(new Gson().toJson(msgcode));
        out.close();

      }
    }
    else
    {
      chain.doFilter(req, resp);
    }
  }

  public void init(FilterConfig filterConfig) {}

  public void destroy() {}
}
