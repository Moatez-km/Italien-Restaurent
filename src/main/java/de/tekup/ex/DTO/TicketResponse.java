package de.tekup.ex.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
	  private LocalDateTime date;
	    private int nbCouvert;
	    private float addition;

}
