package com.br.robson.core.model.vo;

import java.util.Date;
import java.util.List;
import com.br.robson.core.model.People;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentVO {

	private Long id;
	
	private String nomeDepartamento;
	
	private Date dataCriacaoDp;
	
	private Integer enable;
	
	private List<People> listaPessoa;
		
}
