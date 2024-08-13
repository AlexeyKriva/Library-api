package api.modsen.library.bookmicroservice.exceptions;

import api.modsen.library.bookmicroservice.config.LibraryAppConstants;

import java.util.List;

public class MultiplyMissingParametersException extends RuntimeException {
    private final List<String> missingParameters;

    public MultiplyMissingParametersException(List<String> missingParameters) {
        super(LibraryAppConstants.MISSING_PARAMETERS_MESSAGE + String.join(", ", missingParameters));
        this.missingParameters = missingParameters;
    }

    public List<String> getMissingParameters() {
        return this.missingParameters;
    }
}
