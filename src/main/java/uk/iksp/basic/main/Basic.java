package uk.iksp.basic.main;

import com.ksptooi.asmc.entity.plugins.ExternalPlugin;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;


public class Basic extends ExternalPlugin{

	
	public static final String version = "3.0-A";
	
	@Override
	public void onEnable(){	
		
		Logger msg = Asmc.getLogger();
		
		msg.sendSysMessage("Asmc_Basic ���سɹ�!");
		msg.sendSysMessage("Asmc_Basic�汾:"+version);
		msg.sendSysMessage("Asmc_Basic:�ṩ�����������������;��� ɾ�� �б� ���� �Զ�����");
		
		
		Asmc.getCorePluginManagerService().regPluginCommand(this, "cmd_add", "uk.iksp.basic.command.type.Cmd_Add");
		Asmc.getCorePluginManagerService().regPluginCommand(this, "cmd_list", "uk.iksp.basic.command.type.Cmd_List");
		Asmc.getCorePluginManagerService().regPluginCommand(this, "cmd_search", "uk.iksp.basic.command.type.Cmd_Search");
		Asmc.getCorePluginManagerService().regPluginCommand(this, "cmd_del", "uk.iksp.basic.command.type.Cmd_Del");
		Asmc.getCorePluginManagerService().regPluginCommand(this, "cmd_auto", "uk.iksp.basic.command.type.Cmd_AutoImport");
		
	}
	
	
	
	@Override
	public ExternalPlugin getThis() {
		return this;
	}

}
