


public class CashCard
{
	private String bankId;
	private int cardId;

	
	public CashCard(String bankId, int cardId)
	{
	   this.bankId = bankId;
	   this.cardId = cardId;
	}
	public String getBankId()
	{
		return bankId;
	}
	
	public void setBankId(String bankId)
	{
		this.bankId = bankId;
	}
	
	public int getCardId()
	{
		return cardId;
	}
	
	public void setCardId(int cardId)
	{
		this.cardId = cardId;
	}	
	
}
