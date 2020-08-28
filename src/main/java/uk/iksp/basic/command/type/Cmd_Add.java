package uk.iksp.basic.command.type;

import java.io.File;
import java.util.ArrayList;

import com.ksptooi.asmc.entity.command.Command;
import com.ksptooi.asmc.entity.command.InputCommand;
import com.ksptooi.asmc.entity.commandType.Command_cmd;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;


public class Cmd_Add implements Command_cmd{

	@Override
	public void ExecuteOfType(Command command) {
		
		Logger msg = Asmc.getLogger();
		
		InputCommand ic = command.getInputCommand();
		
		ArrayList<String> parameter = ic.getParameter();
		
		//判断参数长度
		if(parameter.size()<3){
			msg.sendWarningMessage("添加语句:>名称,地址,类型,描述");
			return;
		}
		
		
		//判断命令是否存在
		if(Asmc.getCommandDataService().isExist(parameter.get(0))){
			msg.sendWarningMessage("添加失败! 命令重复.");
			return;
		}
		
		
		Command commandEntity = new Command();
		
		File file = new File(parameter.get(1));
		
		
		if( !file.isDirectory()&& !file.isFile()){
			
			commandEntity.setPath(parameter.get(1));
			commandEntity.setFile("-");
			
		}
		
		
		if(file.isFile()){
			
			commandEntity.setPath(file.getParent().replace("\\", "/").replace("\\\\", "/")+"/");
			commandEntity.setFile(file.getName());
			
		}
		
		if(file.isDirectory()){
			commandEntity.setPath(file.getParent().replace("\\", "/").replace("\\\\", "/")+"/");
			commandEntity.setFile("");
		}
		
		if(parameter.size()>=4){
			commandEntity.setTitle(parameter.get(3));
		}else{
			commandEntity.setTitle("auto import title");
		}
		
		
		if(Asmc.getCommandTypeScannerService().getType(parameter.get(2)) == null){
			msg.sendWarningMessage("命令添加失败! "+parameter.get(2)+" 不是一个正确的命令类型");
			return;
		}
		
		commandEntity.setName(parameter.get(0));
		commandEntity.setType(parameter.get(2));	
		commandEntity.setCca(Asmc.getUserPermissionService().getActiveUser().getUserName());

		
		
		Asmc.getCommandDataService().insert(commandEntity);
		
		
		msg.successMessage("命令:"+commandEntity.getName()+"已成功添加!");
		
	}
	
	
	
	@Override
	public Command_cmd getThis() {
		return this;
	}

	
	
	
	
}
