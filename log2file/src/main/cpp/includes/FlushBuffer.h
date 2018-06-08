//
// Created by pqpo on 2018/2/10.
//

#ifndef LOG4A_FLUSHBUFFER_H
#define LOG4A_FLUSHBUFFER_H

#include <sys/types.h>
#include <string.h>
#include <math.h>
#include <stdio.h>

class FlushBuffer {

    public:
        FlushBuffer(char* log_file, size_t size = 128);
        ~FlushBuffer();
        void write(void* data, size_t len);
        void reset();
        size_t length();
        void* ptr();
        char* logFile();

    private:
        char* log_file = nullptr;

        char* data_ptr = nullptr;
        char* write_ptr = nullptr;
        size_t capacity;

        size_t emptySize();

};


#endif //LOG4A_FLUSHBUFFER_H
