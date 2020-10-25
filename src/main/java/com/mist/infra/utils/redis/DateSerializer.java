package com.mist.infra.utils.redis;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mist.infra.utils.BaseConstants;

import java.io.IOException;
import java.util.Date;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 * description
 *
 * @author heng.huang@hand-china.com 2020/10/20
 */
public class DateSerializer extends JsonSerializer<Date> {
    private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance(BaseConstants.Pattern.DATETIME_SSSZ);

    public DateSerializer() {
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeString(DATE_FORMAT.format(date));
    }
}
