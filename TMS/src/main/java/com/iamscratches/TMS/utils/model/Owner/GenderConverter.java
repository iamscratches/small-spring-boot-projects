package com.iamscratches.TMS.utils.model.Owner;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender.getLabel();
    }

    @Override
    public Gender convertToEntityAttribute(String s) {
        return Gender.findByLabel(s);
    }
}
