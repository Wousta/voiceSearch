package agentes.launcher;

public enum AgentModel {

	GRABADORA("Microphone"),
	RECOGNIZER("Recognizer"),
	INTERFAZ("Interfaz"),
	DESCONOCIDO("Desconocido");

    private final String value;

    AgentModel(String value){ 
    	this.value = value; 
    	}

    public String getValue(){ 
    	return this.value; 
    }

    public static AgentModel getEnum(String value) {
        switch (value) {
            case "Microphone":
                return GRABADORA;
            case "Recognizer":
                return RECOGNIZER;
            case "Interfaz":
                return INTERFAZ;
            default:
                return DESCONOCIDO;
        }
    }
	
}