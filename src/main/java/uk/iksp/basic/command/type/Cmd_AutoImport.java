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
		
		
		if(parameter.size()<2){
			msg.sendWarningMessage("用法: ?>参数1,参数2ַ");
			return;
		}
		
		
		
		if(Asmc.getCommandDataService().isExist(parameter.get(0))){
			msg.sendWarningMessage("用法: ?>参数1,参数2ַ");
			return;
		}
		
		File file = new File(parameter.get(1));
		
		Command entity = new Command();
		
		try{
			
			entity.setName(parameter.get(0));
			entity.setType("cmd_link");
			entity.setTitle("Auto Import");
			entity.setCca(Asmc.getUserPermissionService().getActiveUser().getUserName());
			entity.setFile(file.getName());
			entity.setPath(file.getParent().replace("\\", "/").replace("\\\\", "/")+"/");	
			
		}catch(Exception e){
			msg.sendWarningMessage("导入失败!");
			return;
		}
		
		

		Asmc.getCommandDataService().insert(entity);
		
		msg.sendWarningMessage("自动导入成功!" + parameter.get(0));
		
	}

	
	
	
	@Override
	public Command_cmd getThis() {
		return this;
	}

	
	
	
	
}
