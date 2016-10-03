package com.goeuro.output;

import java.io.IOException;

/**
 * Exporter interface to be implemented by all exporter types
 * @author muhammad sobhy
 * @version 1.0
 */
public interface Exporter {
    
    /**
     * Export any data
     * @throws IOException 
     */
    void export() throws IOException;
    
    /**
     * Export type
     * @return export type
     */
    String exportType();
    
}
