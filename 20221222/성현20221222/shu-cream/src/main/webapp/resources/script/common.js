
function movepage(page){
	
	location.href = "http://192.168.0.82/shu-cream/"+page;

}

function lengthCheck(obj){
	const length =[["UserId",5,20],["UserName",2,5],["passWord",5,15],["UserPhone",11,11],
				  ["UserAddr",0,20]];
	let result = null;
	
	for(let recordIdx=0; recordIdx<length.length; recordIdx++){
		if(obj.getAttribute("name") == length[recordIdx][0]){
			result = (obj.value.length >= length[recordIdx][1] && 
			          obj.value.length <= length[recordIdx][2])? true : false;
		break;
		}else{
			alert("올바르지못한 접근입니다....");
		}
	}
	return result;
}

function createForm(name, action, method){
	const form = document.createElement("form");
	if(name !== "") form.setAttribute("name",name);
	form.setAttribute("action",action);
	form.setAttribute("method",method);
	
	
	return form;
}
/* Input Box 생성*/
function createInputBox(type, name, value, placeholder){
	const input = document.createElement("input");
	input.setAttribute("type", type);
	input.setAttribute("name", name);
	if(value != "") input.setAttribute("value", value);
	if(placeholder != "") input.setAttribute("placeholde", placeholder);
	return input;
}


/* 페이지 이동 */
function serverCall(Page){
	
	if(targetPage == '1'){
	const form = createForm("", "login","get");
		
	let group = [];
	group.push(document.getElementsByName("UserId")[0]) 
	group.push(document.getElementsByName("passWord")[0]) 
	form.appendChild(group[0]);	
		
	document.body.appendChild(form);
	form.submit();	
	
	}else if(targetPage == '2'){
		location.href = "http://192.168.0.82/shu-cream/Registration.jsp";
	}
	else{
	const form = createForm("", "MovePage", "get");
	const input = createInputBox("hidden", "target", targetPage, "");
	
	let group = [];
	group.push(document.getElementsByName("groupName")[0]) 
	form.appendChild(group[0]);

	form.appendChild(input);
	document.body.appendChild(form);
	
	form.submit();	
		
	}
}

