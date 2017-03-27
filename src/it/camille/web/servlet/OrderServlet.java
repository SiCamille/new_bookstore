package it.camille.web.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.camille.domain.Cart;
import it.camille.domain.CartItem;
import it.camille.domain.Order;
import it.camille.domain.OrderItem;
import it.camille.domain.User;
import it.camille.service.OrderService;
import it.camille.utils.BaseServlet;
import it.camille.utils.PaymentUtil;
import it.camille.utils.UUIDUtils;

/**
 * 订单WEB层控制类
 * 
 * @author Camille
 * @version 1.0,2016-12-18 16:25:57
 */
@WebServlet(urlPatterns = "/orderServlet")
public class OrderServlet extends BaseServlet {

	/**
	 * 将购物车中信息添加至订单
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 */
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) {
		// 从会话中获取参数,如参数为空,则给出提示
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			request.setAttribute("msg", "您还未购物,请先购物");
			return "jsps/msg.jsp";
		}
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("msg", "您还未登陆,请先登录");
			return "jsps/msg.jsp";
		}

		// 创建订单对象,设置初始化参数
		Order order = new Order();
		order.setOid(UUIDUtils.getUUID());
		order.setAddress(null);
		order.setOrdertime(new Date());
		order.setState(1);
		order.setUser(user);
		order.setTotal(cart.getTotal());

		// 获取购物项,并存储至订单中
		Collection<CartItem> cartItems = cart.getCartItems();
		for (CartItem cartItem : cartItems) {
			OrderItem item = new OrderItem();
			item.setIid(UUIDUtils.getUUID());
			item.setCount(cartItem.getCount());
			item.setBook(cartItem.getBook());
			item.setSubtotal(cartItem.getSubtotal());
			item.setOrder(order);
			order.getOrderItems().add(item);
		}

		// 调用业务层,将订单存储至数据库中
		OrderService service = new OrderService();
		service.save(order);

		// 清空购物车
		cart.clearCart();

		// 调用业务层获取订单
		order = service.findByOid(order.getOid());

		// 将订单存储在request域中
		request.setAttribute("order", order);

		// 请求转发
		return "/jsps/order/desc.jsp";
	}

	/**
	 * 根据用户获取订单
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 */
	public String findByUser(HttpServletRequest request, HttpServletResponse response) {
		// 通过会话获取用户
		User user = (User) request.getSession().getAttribute("user");

		// 调用业务层通过用户ID获取订单
		OrderService service = new OrderService();
		List<Order> list = service.findByUid(user.getUid());

		// 将订单存储在request域中
		request.setAttribute("list", list);

		// 请求转发
		return "/jsps/order/list.jsp";
	}

	/**
	 * 取消订单的方法
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		// 获取订单ID
		String oid = request.getParameter("oid");

		// 调用业务层删除订单的方法
		OrderService service = new OrderService();
		service.delete(oid);

		return findByUser(request, response);
	}

	/**
	 * 根据订单ID查询订单
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 */
	public String findByOid(HttpServletRequest request, HttpServletResponse response) {
		// 获取参数
		String oid = request.getParameter("oid");

		// 调用业务层根据订单ID查询订单实体
		OrderService service = new OrderService();
		Order order = service.findByOid(oid);

		// 将订单ID封装至request域中
		request.setAttribute("order", order);

		return "/jsps/order/desc.jsp";
	}

	/**
	 * 为订单付款
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 * @throws IOException
	 */
	public String payOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 接收参数
		String oid = request.getParameter("oid");
		String pd_FrpId = request.getParameter("pd_FrpId");
		String address = request.getParameter("address");
		
		if (address == null || address.trim().length() == 0 || address.isEmpty()) {
			request.setAttribute("msg", "订单地址不能为空");
			return "/jsps/msg.jsp";
		}

		// 修改订单地址
		OrderService service = new OrderService();
		Order order = service.findByOid(oid);
		User user = (User) request.getSession().getAttribute("user");
		order.setUser(user);
		order.setAddress(address);
		service.update(order);
		
		// 封装支付数据
		String p0_Cmd = "Buy"; // 业务类型
		String p1_MerId = "10001126856"; // 商户编号
		String p2_Order = oid; // 商户订单号
		String p3_Amt = "0.01"; // 支付金额
		String p4_Cur = "CNY"; // 支付币种
		String p5_Pid = ""; // 商品名称
		String p6_Pcat = ""; // 商品种类
		String p7_Pdesc = ""; // 商品描述
		String p8_Url = "localhost:8080/newBookstore/orderServlet?method=backOrder"; // 商户接收支付成功数据的地址
		String p9_SAF = ""; // 送货地址
		String pa_MP = ""; // 商户扩展信息
		String pr_NeedResponse = "1"; // 应答机制
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue); // 签名数据

		// 获取路径
		StringBuffer buffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		buffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		buffer.append("p1_MerId=").append(p1_MerId).append("&");
		buffer.append("p2_Order=").append(p2_Order).append("&");
		buffer.append("p3_Amt=").append(p3_Amt).append("&");
		buffer.append("p4_Cur=").append(p4_Cur).append("&");
		buffer.append("p5_Pid=").append(p5_Pid).append("&");
		buffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		buffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		buffer.append("p8_Url=").append(p8_Url).append("&");
		buffer.append("p9_SAF=").append(p9_SAF).append("&");
		buffer.append("pa_MP=").append(pa_MP).append("&");
		buffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		buffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		buffer.append("hmac=").append(hmac);

		// 重定向至支付页面
		response.sendRedirect(buffer.toString());

		return null;
	}

	/**
	 * 接受支付返回参数的方法
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 */
	public String backOrder(HttpServletRequest request, HttpServletResponse response) {
		// 接收参数
		String p1_MerId = request.getParameter("p1_MerId"); // 商户编号
		String r0_Cmd = request.getParameter("r0_Cmd"); // 业务类型
		String r1_Code = request.getParameter("r1_Code"); // 支付结果
		String r2_TrxId = request.getParameter("r2_TrxId"); // 易宝支付交易流水号
		String r3_Amt = request.getParameter("r3_Amt"); // 支付金额
		String r4_Cur = request.getParameter("r4_Cur"); // 交易币种
		String r5_Pid = request.getParameter("r5_Pid"); // 商品名称
		String r6_Order = request.getParameter("r6_Order"); // 商户订单号
		String r7_Uid = request.getParameter("r7_Uid"); // 易宝支付会员ID
		String r8_MP = request.getParameter("r8_MP"); // 商户扩展信息
		String r9_BType = request.getParameter("r9_BType"); // 交易结果返回类型
		String hmac = request.getParameter("hmac"); // 数字签名
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";

		// 获取结果
		boolean flag = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid,
				r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);

		// 判断结果是否为真
		if(flag) {
			OrderService service = new OrderService();
			Order order = service.findByOid(r6_Order);
			User user = (User) request.getSession().getAttribute("user");
			order.setUser(user);
			order.setState(2);
			service.update(order);
			request.setAttribute("msg", "亲,您已为订单" + r6_Order + "付款成功,付款金额为" + r3_Amt + "元");
		} else {
			request.setAttribute("msg", "亲,不好意思,付款失败");
		}
		return "/jsps/msg.jsp";
	}
}
