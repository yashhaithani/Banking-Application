package dto;  // used to transfer Data between Client and Server

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto
{
    private long id;
    private  String accountHolderName;
    private double balance;
}
