package de.tekup.ex.DTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableRequest {
	private int nbCouvert;
	private String type;
	private float supplement;

}
