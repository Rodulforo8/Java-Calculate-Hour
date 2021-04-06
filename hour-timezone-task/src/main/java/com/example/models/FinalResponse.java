package com.example.models;

public class FinalResponse {

    private String response;
    private String utc;
    
    public FinalResponse() {
   	 
    }
    
    

	public FinalResponse(String response, String utc) {
		this.response = response;
		this.utc = utc;
	}



	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getUtc() {
		return utc;
	}

	public void setUtc(String utc) {
		this.utc = utc;
	}
    
}
