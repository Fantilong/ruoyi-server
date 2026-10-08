-- ----------------------------
-- 借阅记录管理模块 - 菜单及权限初始化脚本
-- 执行前请确保已执行 ssk_book_category.sql、ssk_book.sql 及 ry_20260320.sql
-- 菜单ID从2011开始（2000-2010已分配给图书分类、图书管理）
-- ----------------------------

-- 二级菜单：借阅记录（挂载在图书管理目录2000下）
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2011, '借阅记录', 2000, 3, 'borrowRecord', 'ssk/borrowRecord/index', '', '', 1, 0, 'C', '0', '0', 'ssk:borrowRecord:list', 'log', 'admin', sysdate(), '', null, '借阅记录菜单');

-- 按钮权限：借阅记录查询
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2012, '借阅记录查询', 2011, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:borrowRecord:query', '#', 'admin', sysdate(), '', null, '');

-- 按钮权限：借阅记录新增
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2013, '借阅记录新增', 2011, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:borrowRecord:add', '#', 'admin', sysdate(), '', null, '');

-- 按钮权限：借阅记录修改
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2014, '借阅记录修改', 2011, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:borrowRecord:edit', '#', 'admin', sysdate(), '', null, '');

-- 按钮权限：借阅记录删除
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2015, '借阅记录删除', 2011, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:borrowRecord:remove', '#', 'admin', sysdate(), '', null, '');

-- 按钮权限：还书
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2016, '还书', 2011, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:borrowRecord:return', '#', 'admin', sysdate(), '', null, '');
