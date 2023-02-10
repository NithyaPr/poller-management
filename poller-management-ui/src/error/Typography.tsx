import { ReactHTML, ReactNode } from 'react';
type Variant = 'headline1' | 'headline2' | 'headline3' | 'headline4' | 'headline5' | 'headline6' | 'body-default' | 'body-default-strong' | 'body-small' | 'body-small-strong' | 'caption' | 'link' | 'link-small' | 'button';
export type Props = {
    /** The style variant to apply */
    variant: Variant;
    /** Html element that is rendered */
    component: keyof Pick<ReactHTML, 'h1' | 'h2' | 'h3' | 'h4' | 'h5' | 'h6' | 'p' | 'span' | 'div' | 'strong'>;
    /** Css color property value of the content. Can be either a key of, or the value of a theme color */
    color?: 'red';
    /** Content of the component */
    children: ReactNode;
};
export declare const Typography: ({ variant, component, color, children }: Props)
 => import("react").DetailedReactHTMLElement<{
    children: ReactNode;
    css: import("@emotion/utils").SerializedStyles;
}, HTMLElement>;

export {};
