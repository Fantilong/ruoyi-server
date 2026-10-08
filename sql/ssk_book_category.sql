-- ----------------------------
-- 图书分类管理模块 - 菜单及权限初始化脚本
-- 执行前请确保当前数据库已初始化 ry_20260320.sql
-- 菜单ID从2000开始，与系统内置菜单（1~1999）区分，避免冲突
-- ----------------------------

-- 一级目录：图书管理
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2000, '图书管理', 0, 4, 'ssk', null, '', '', 1, 0, 'M', '0', '0', '', 'education', 'admin', sysdate(), '', null, '图书管理目录');

-- 二级菜单：图书分类
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2001, '图书分类', 2000, 1, 'bookCategory', 'ssk/bookCategory/index', '', '', 1, 0, 'C', '0', '0', 'ssk:bookCategory:list', 'tree', 'admin', sysdate(), '', null, '图书分类菜单');

-- 按钮权限：类目查询
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2002, '类目查询', 2001, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:bookCategory:query', '#', 'admin', sysdate(), '', null, '');

-- 按钮权限：类目新增
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2003, '类目新增', 2001, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:bookCategory:add', '#', 'admin', sysdate(), '', null, '');

-- 按钮权限：类目修改
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2004, '类目修改', 2001, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:bookCategory:edit', '#', 'admin', sysdate(), '', null, '');

-- 按钮权限：类目删除
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2005, '类目删除', 2001, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:bookCategory:remove', '#', 'admin', sysdate(), '', null, '');
