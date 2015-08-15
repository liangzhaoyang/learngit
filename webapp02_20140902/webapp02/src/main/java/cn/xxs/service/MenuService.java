package cn.xxs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.MenuDao;
import cn.xxs.entity.Menu;
import cn.xxs.entity.MenuItem;

@Service
public class MenuService {
	@Autowired
	private MenuDao menuDao;
	
	public List<MenuItem> getMenusByRoleId(int roleId) {
		List<Menu> menus = menuDao.getMenusByRoleId(roleId);
		
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		for(Menu menu : menus) {
			String firstNameTmp = menu.getFirstName();
			String entryPoint = menu.getEntryPoint();
			boolean bFound = false;
			for(int i = 0; i < menuItems.size(); ++i) {
				MenuItem menuItemTmp = menuItems.get(i);
				if(menuItemTmp.getName().equals(firstNameTmp)) {
					MenuItem menuItemNew = new MenuItem();
					menuItemNew.setName(menu.getSecondName());
					menuItemNew.setEntryPoint(entryPoint);
					
					menuItemTmp.getSubItems().add(menuItemNew);
					bFound = true;
					break;
				}
			}
			
			if(!bFound) {
				MenuItem menuItemNew = new MenuItem();
				if(menu.getSecondName().isEmpty()) {
					menuItemNew.setName(firstNameTmp);
					menuItemNew.setEntryPoint(entryPoint);
				} else {
					MenuItem subMenuItemNew = new MenuItem();
					subMenuItemNew.setName(menu.getSecondName());
					subMenuItemNew.setEntryPoint(entryPoint);
					
					List<MenuItem> subItems = new ArrayList<MenuItem>();
					subItems.add(subMenuItemNew);
					
					menuItemNew.setName(firstNameTmp);
					menuItemNew.setSubItems(subItems);
				}
				menuItems.add(menuItemNew);
			}
		}
		return menuItems;
	}
}
