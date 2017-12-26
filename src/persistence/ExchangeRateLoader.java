package persistence;

import model.Currency;
import model.ExchangeRate;

public interface ExchangeRateLoader {

    ExchangeRate load(Currency from, Currency to);
}
