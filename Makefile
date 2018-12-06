NAME	=		dist/trantor-1.0.0.jar

GRADLE=		gradle

RM		=		rm -f

all		:		$(NAME)

$(NAME):
					$(GRADLE) jar

package:
					$(GRADLE) assemble

build	:
					$(GRADLE) build

run		:
					$(GRADLE) run

test	:
					$(GRADLE) test

deps	:
					$(GRADLE) dependencies

clean	:
					$(GRADLE) clean

fclean:		clean

re		:		fclean all

.PHONY : all package build run test deps clean re
