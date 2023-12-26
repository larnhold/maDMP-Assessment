package org.arnhold.evaluation.shacl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.jena.shacl.ValidationReport;
import org.apache.jena.shacl.validation.ReportEntry;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ShaclValidationResult {
    public String maDMP;
    public String shape;
    public Boolean conforms;
    public List<String> messages;

    public ShaclValidationResult(String madmp, String shape, ValidationReport report) {
        this.maDMP = madmp;
        this.shape = shape;
        this.conforms = report.conforms();
        this.messages = report.getEntries().stream().map(ReportEntry::message).collect(Collectors.toList());
    }
}
