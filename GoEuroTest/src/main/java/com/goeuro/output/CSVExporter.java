package com.goeuro.output;

import java.util.List;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CSV exporter
 * @author muhammad sobhy
 * @version 1.0
 */
public class CSVExporter implements Exporter{
    
    private String fileName;
    private String[] header;
    private List<String[]> data;

    public CSVExporter(String fileName, String[] header, List<String[]> data) {
        this.fileName = fileName;
        this.header = header;
        this.data = data;
    }
    
    /**
     * Export data to csv file
     * @throws IOException 
     */
    @Override
    public void export() throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            writer.writeNext(header);
            writer.writeAll(data);
        }
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }    

    /**
     * Get export type
     * @return export type
     */
    @Override
    public String exportType() {
        return "CSV Exporter";
    }
    
}
