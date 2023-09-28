package org.arnhold.evaluation.shacl;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShaclValidationResult {
    private String shape;
    private Boolean confirms;
}
