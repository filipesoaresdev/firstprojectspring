package com.firstproject.filipe.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.firstproject.filipe.domain.enums.ClientType;
import com.firstproject.filipe.dto.ClientNewDTO;
import com.firstproject.filipe.resources.exceptions.FieldMessage;
import com.firstproject.filipe.services.validation.utils.BR;

public class ClientInsertValidator implements 
		ConstraintValidator<ClientInsert, ClientNewDTO> {

	@Override
	public void initialize(ClientInsert ann) {
		
	}
	
	@Override
	public boolean isValid(ClientNewDTO objDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDTO.getClientType().equals(ClientType.NATURALPERSON.getCod())
				&& !BR.isValidCpf(objDTO.getCpfOrCnpj()) ) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF"));
			
		}else if(objDTO.getClientType().equals(ClientType.LEGALPERSON.getCod())
				&& !BR.isValidCnpj(objDTO.getCpfOrCnpj()) ) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ"));

		}
		
		//insert errors in the list
		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
				.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
			
		}
		
		return list.isEmpty();
		
	}; 
	
}
