cmake_minimum_required(VERSION 2.6.4)
project(SBN_APP C)



# workaround until these definitions are exposed by cfe_sb
include_directories(${CMAKE_SOURCE_DIR}/fsw/cfe-core/src/sb)

include_directories(fsw/platform_inc)
include_directories(fsw/tables)

aux_source_directory(fsw/src APP_SRC_FILES)

# Create the app module
add_cfe_app(sbn ${APP_SRC_FILES})

#aux_source_directory(fsw/tables APP_TBL_FILES)
#add_cfe_tables(sbn ${APP_TBL_FILES})
