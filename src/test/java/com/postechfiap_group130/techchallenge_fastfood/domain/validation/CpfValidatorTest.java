package com.postechfiap_group130.techchallenge_fastfood.domain.validation;

import com.postechfiap_group130.techchallenge_fastfood.application.validation.CpfValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CpfValidatorTest {

    @Test
    void whenCpfIsNull_thenReturnFalse() {
        assertFalse(CpfValidator.isValid(null));
    }

    @Test
    void whenCpfIsEmpty_thenReturnFalse() {
        assertFalse(CpfValidator.isValid(""));
    }

    @Test
    void whenCpfHasInvalidLength_thenReturnFalse() {
        assertFalse(CpfValidator.isValid("1234567890")); // 10 digits
        assertFalse(CpfValidator.isValid("123456789012")); // 12 digits
    }

    @Test
    void whenCpfHasAllSameDigits_thenReturnFalse() {
        assertFalse(CpfValidator.isValid("11111111111"));
        assertFalse(CpfValidator.isValid("22222222222"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "12345678900", // Invalid CPF
        "11111111111", // All digits are the same
        "123.456.789-00", // Invalid CPF with formatting
        "123456789012", // CPF with more than 11 digits
        "1234567890", // CPF with less than 11 digits
        "abc.def.ghi-jk", // CPF with letters
        "123.456.789-0a" // CPF with letter at the end
    })
    void whenCpfIsInvalid_thenReturnFalse(String cpf) {
        assertFalse(CpfValidator.isValid(cpf));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "52998224725", // Valid CPF without formatting
        "529.982.247-25", // Valid CPF with formatting
        "12345678909", // Valid CPF without formatting
        "123.456.789-09" // Valid CPF with formatting
    })
    void whenCpfIsValid_thenReturnTrue(String cpf) {
        assertTrue(CpfValidator.isValid(cpf));
    }
} 