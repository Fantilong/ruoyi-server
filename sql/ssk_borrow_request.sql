-- ----------------------------
-- 借阅申请管理模块 - 菜单及权限初始化脚本
-- 执行前请确保已执行 ssk_book_category.sql、ssk_book.sql、ssk_borrow_record.sql 及 ry_20260320.sql
-- 菜单ID从2017开始（2000-2016已分配给图书分类、图书管理、借阅记录）
-- ----------------------------

-- 二级菜单：借阅申请（挂载在图书管理目录2000下）
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2017, '借阅申请', 2000, 4, 'borrowRequest', 'ssk/borrowRequest/index', '', '', 1, 0, 'C', '0', '0', 'ssk:borrowRequest:list', 'form', 'admin', sysdate(), '', null, '借阅申请菜单');

-- 按钮权限：借阅申请查询
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2018, '借阅申请查询', 2017, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:borrowRequest:query', '#', 'admin', sysdate(), '', null, '');

-- 按钮权限：借阅申请同意
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2019, '同意申请', 2017, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:borrowRequest:approve', '#', 'admin', sysdate(), '', null, '');

-- 按钮权限：借阅申请拒绝
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2020, '拒绝申请', 2017, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:borrowRequest:reject', '#', 'admin', sysdate(), '', null, '');

-- 按钮权限：借阅申请删除
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2021, '借阅申请删除', 2017, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:borrowRequest:remove', '#', 'admin', sysdate(), '', null, '');
