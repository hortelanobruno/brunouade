drop procedure if exists get_quota;
delimiter //
create procedure get_quota (subscriber varchar(45),serv varchar(45))
begin
declare doseQV integer default NULL;
declare doseQT integer default NULL;
declare typeee varchar(45);

select substring(serv,2) into serv;

select if(qq.value-qqcc.dosage < 0, qq.value, qqcc.dosage) INTO doseQV from portalcv_quota_config qqcc join portalcv_subscribers subs on qqcc.plan=subs.groupname and subs.username=subscriber join portalcv_quota qq on subs.username=qq.username and qqcc.service=qq.service and qqcc.type=qq.type where qq.type = 'QV' and qq.service=serv;

select if(qq.value-qqcc.dosage < 0, qq.value, qqcc.dosage) INTO doseQT from portalcv_quota_config qqcc join portalcv_subscribers subs on qqcc.plan=subs.groupname and subs.username=subscriber join portalcv_quota qq on subs.username=qq.username and qqcc.service=qq.service and qqcc.type=qq.type where qq.type = 'QT' and qq.service=serv;

if doseQV is null then
	if doseQT is not null then
		update portalcv_quota set value=value-doseQT where username=subscriber and service=serv and type='QT';
		if doseQT <= 0 then 
			(select 1 as id,subscriber as username,'Idle-Timeout' as attribute,'100' as value,'+=' as op) UNION 
				(select 2 as id,username,attribute,concat('QT',doseQT) as value,op from portalcv_quota where username=subscriber and service=serv and type='QT');
		else
			select ID,username,attribute,concat('QT',doseQT) as value,op from portalcv_quota where username=subscriber and service=serv and type='QT';
		end if;
	end if;
else
	if doseQT is null then
		update portalcv_quota set value=value-doseQV where username=subscriber and service=serv and type='QV';
		if doseQV <= 0 then 
			(select 1 as id,subscriber as username,'Idle-Timeout' as attribute,'100' as value,'+=' as op) UNION 
				(select 2 as id,username,attribute,concat('QV',doseQV) as value,op from portalcv_quota where username=subscriber and service=serv and type='QV');
		else
			select ID,username,attribute,concat('QV',doseQV) as value,op from portalcv_quota where username=subscriber and service=serv and type='QV';
		end if;
	else
		update portalcv_quota set value=value-doseQV where username=subscriber and service=serv and type='QV';
		update portalcv_quota set value=value-doseQT where username=subscriber and service=serv and type='QT';
		if doseQV <= 0 then 
			(select 1 as id,subscriber as username,'Idle-Timeout' as attribute,'100' as value,'+=' as op) UNION 
				(select 2 as id,username,attribute,concat('QV',doseQV) as value,op from portalcv_quota where username=subscriber and service=serv and type='QV') UNION 
				(select 3 as id,username,attribute,concat('QT',doseQT) as value,op from portalcv_quota where username=subscriber and service=serv and type='QT');
		else
			(select ID,username,attribute,concat('QV',doseQV) as value,op from portalcv_quota where username=subscriber and service=serv and type='QV') UNION 
				(select ID,username,attribute,concat('QT',doseQT) as value,op from portalcv_quota where username=subscriber and service=serv and type='QT');
		end if;
	end if;
end if;

end//
delimiter ;
