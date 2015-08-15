package cn.xxs.paging;

import org.apache.log4j.Logger;

public class PageContext extends Page {
	private static final long serialVersionUID = -5537163824451653176L;
	private static final Logger _logger = Logger.getLogger(PageContext.class);

	private static ThreadLocal<PageContext> context = new ThreadLocal<PageContext>();

	public static PageContext getContext() {
		PageContext ci = context.get();
		if (ci == null) {
			ci = new PageContext();
			context.set(ci);
		}
		return ci;
	}

	public static void removeContext() {
		context.remove();
	}

	protected void initialize() {
	}
}
