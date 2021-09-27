package org.konverter.orchestration;

import java.util.concurrent.Callable;

public interface KonverterRequest extends Callable<byte[]> {
}
