package uk.iksp.basic.command.type;

import java.io.File;
import java.util.ArrayList;

import com.ksptooi.asmc.entity.command.Command;
import com.ksptooi.asmc.entity.command.InputCommand;
import com.ksptooi.asmc.entity.commandType.Command_cmd;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;

public class Cmd_AutoImport implements Command_cmd {

	
	
	@Override
	public void ExecuteOfType(Command command) {
		
		InputCommand ic = command.getInputCommand();
		
		ArrayList<String> parameter = ic.getParameter();
		Logger msg = Asmc.getLogger();
		
		//判断参数长度
		if(parameter.size()<2){
			msg.sendWarningMessage("自动导入:>名称,地址");
			return;
		}
		
		
		//判断命令是否存在
		if(Asmc.getCommandDataService().isExist(parameter.get(0))){
			msg.sendWarningMessage("添加失败! 命令重复.");
			return;
		}
		
		File file = new File(parameter.get(1));
		
		Command entity = new Command();
		
		try{
			
			entity.setName(parameter.get(0));
			entity.setType("cmd_link");
			entity.setTitle("自动导入的命令");
			entity.setCca(Asmc.getUserPermissionService().getActiveUser().getUserName());
			entity.setFile(file.getName());
			entity.setPath(file.getParent().replace("\\", "/").replace("\\\\", "/")+"/");	
			
		}catch(Exception e){
			msg.sendWarningMessage("添加失败! 路径解析错误!");
			return;
		}
		
		

		Asmc.getCommandDataService().insert(entity);
		
		msg.sendWarningMessage("自动导入成功! 命令:" + parameter.get(0));
		
	}

	
	
	
	@Override
	public Command_cmd getThis() {
		return this;
	}

	
	
	
	
}
