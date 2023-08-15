#set(withLombok = voConfig.isWithLombok())
package #(packageConfig.voPackage);

#for(importClass : table.buildDtoAndVoImports())
import #(importClass);
#end

#if(withLombok)
import lombok.*;
import lombok.experimental.Accessors;
#end

/**
 * #(table.getComment()) Vo类。
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */
#if(withLombok)
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
#end
public class #(table.buildVoClassName())#(table.buildExtends())#(table.buildImplements()) {
#for(column : table.columns)
    #set(comment = javadocConfig.formatColumnComment(column.comment))
    #if(isNotBlank(comment))
    /**
     * #(comment)
     */
    #end
    private #(column.propertySimpleType) #(column.property);
#end

#if(!withLombok)
    #for(column: table.columns)
    public #(column.propertySimpleType) #(column.getterMethod())() {
        return #(column.property);
    }

    public #(table.buildVoClassName()) #(column.setterMethod())(#(column.propertySimpleType) #(column.property)) {
        this.#(column.property) = #(column.property);
        return this;
    }

    #end
#end
}
