
aux_source_directory(. LIB_SRC)

SET(
        INCLUDE_DIR
        ../include
)
set(CMAKE_C_FLAGS "-std=c99")
INCLUDE_DIRECTORIES(${INCLUDE_DIR})


ADD_LIBRARY(PointerShared  SHARED ${LIB_SRC})

