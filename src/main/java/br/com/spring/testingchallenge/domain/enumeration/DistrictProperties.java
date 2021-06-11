package br.com.spring.testingchallenge.domain.enumeration;

import br.com.spring.testingchallenge.domain.exceptions.DistrictNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DistrictProperties {
    BROOKLIN("Brooklin", 3500.00, "BRL"),
    JARDIM_MARAJOARA("Jardim Marajoara", 2800.50, "BRL"),
    ALTO_DA_BOA_VISTA("Alto Da Boa Vista", 2848.48, "BRL"),
    ITAIM_BIBI("Itaim Bibi", 4000.15, "BRL"),
    ALPHAVILLE("Alphaville", 4200.25, "BRL");

    private final String district;

    private final Double pricePerSqMeter;

    private final String currency;

    public static DistrictProperties getBy(final String district) {
        for (final DistrictProperties districtProperties : DistrictProperties.values()) {
            final boolean districtExists = districtProperties.getDistrict().equals(district);
            if (districtExists) {
                return districtProperties;
            }
        }
        throw new DistrictNotFoundException();
    }
}
