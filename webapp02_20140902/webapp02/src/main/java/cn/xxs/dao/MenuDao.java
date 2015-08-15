package cn.xxs.dao;

import java.util.List;

import cn.xxs.entity.Menu;

public interface MenuDao {
	public List<Menu> getMenusByRoleId(int roleId);
}
